package com.devtcc.tccback.resources;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.entities.Checklist;
import com.devtcc.tccback.services.ChecklistService;

@RestController
@RequestMapping(value = "/checklist")
@CrossOrigin(origins = "http://localhost:3000")
public class ChecklistResource {
	
	@Autowired
	private ChecklistService service;
	
	@GetMapping
	public ResponseEntity<List<Checklist>> findAll(){
		List<Checklist> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Checklist> findById(@PathVariable Long id){
		Checklist checklist = service.findById(id);
		return ResponseEntity.ok().body(checklist);
	}	
	
	 @GetMapping(value = "/ativo/{ativo_id}")
	public ResponseEntity<Checklist> findByAtivoId(@PathVariable Long ativo_id) {
	    Checklist checklist = service.findByAtivoId(ativo_id);
	    return ResponseEntity.ok().body(checklist);
	}

	
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Checklist> insert(
	        @RequestParam("nome") String nome,
	        @RequestParam("dominio") String dominio,
	        @RequestParam("revisao") Boolean revisao,
	        @RequestParam("refinamento") Boolean refinamento,
	        @RequestParam("teste") Boolean teste,
	        @RequestParam("homologacao") Boolean homologacao, 
	        @RequestPart("docrefi") MultipartFile docrefi,
	        @RequestPart("docteste") MultipartFile docteste, 
	        @RequestPart("dochomo") MultipartFile dochomo,
	        @RequestParam("ativo_id") Long id) {

	    Checklist obj = new Checklist(); 
	    obj.setNome(nome);
	    obj.setDominio(dominio);
	    obj.setRevisao(revisao);
	    obj.setRefinamento(refinamento);
	    obj.setTeste(teste);
	    obj.setHomologacao(homologacao);

	    try {
	    
	        // Set the file as byte array
	        obj.setDocrefi(docrefi.getBytes());
	        
	        obj.setDocteste(docteste.getBytes());
	        
	        obj.setDochomo(dochomo.getBytes());
	        
	        obj = service.insert(obj, id);
	        
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).body(obj);
	  
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).build();
	    }
	}
	
	@PutMapping(value = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<Checklist> update(
	        @PathVariable Long id,
	        @RequestParam(value = "nome", required = false) String nome,
	        @RequestParam(value = "dominio", required = false) String dominio,
	        @RequestParam(value = "revisao", required = false) Boolean revisao,
	        @RequestParam(value = "refinamento", required = false) Boolean refinamento,
	        @RequestParam(value = "teste", required = false) Boolean teste,
	        @RequestParam(value = "homologacao", required = false) Boolean homologacao, 
	        @RequestPart(value = "docrefi", required = false) MultipartFile docrefi,
	        @RequestPart(value = "docteste", required = false) MultipartFile docteste, 
	        @RequestPart(value = "dochomo", required = false) MultipartFile dochomo) {

	    Checklist obj = new Checklist(); 
	    obj.setNome(nome);
	    obj.setDominio(dominio);
	    obj.setRevisao(revisao);
	    obj.setRefinamento(refinamento);
	    obj.setTeste(teste);
	    obj.setHomologacao(homologacao);

	    try {
	        // Carregar o checklist existente
	        Checklist existingChecklist = service.findById(id);

	        // Atualizar apenas se o arquivo não for nulo
	        if (docrefi != null && !docrefi.isEmpty()) {
	            obj.setDocrefi(docrefi.getBytes());
	        } else {
	            obj.setDocrefi(existingChecklist.getDocrefi()); // Manter o existente
	        }

	        if (docteste != null && !docteste.isEmpty()) {
	            obj.setDocteste(docteste.getBytes());
	        } else {
	            obj.setDocteste(existingChecklist.getDocteste()); // Manter o existente
	        }

	        if (dochomo != null && !dochomo.isEmpty()) {
	            obj.setDochomo(dochomo.getBytes());
	        } else {
	            obj.setDochomo(existingChecklist.getDochomo()); // Manter o existente
	        }

	        obj.setId(existingChecklist.getId()); // Garantir que o ID do checklist seja mantido
	        obj = service.update(id, obj); // Atualizar o checklist

	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).body(obj);
	      
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).build();
	    }
	}

	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(
	    @RequestParam Long ativo_id, 
	    @RequestParam String fileType // fileType será "docrefi", "docteste", ou "dochomo"
	) {
	    Checklist checklist = service.findByAtivoId(ativo_id);
	    if (checklist == null) {
	        return ResponseEntity.notFound().build();
	    }

	    byte[] fileData;
	    switch (fileType) {
	        case "docrefi":
	            fileData = checklist.getDocrefi();
	            break;
	        case "docteste":
	            fileData = checklist.getDocteste();
	            break;
	        case "dochomo":
	            fileData = checklist.getDochomo();
	            break;
	        default:
	            return ResponseEntity.badRequest().body(null);
	    }

	    if (fileData == null) {
	        return ResponseEntity.notFound().build();
	    }

	    ByteArrayResource resource = new ByteArrayResource(fileData);
	    String mimeType = determineMimeType(fileData);
	    String fileExtension = determineFileExtension(mimeType);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_TYPE, mimeType != null ? mimeType : MediaType.APPLICATION_OCTET_STREAM_VALUE);
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + checklist.getNome() + "_" + fileType + "." + fileExtension + "\"");

	    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	private String determineMimeType(byte[] arquivo) {
	    Tika tika = new Tika();
	    return tika.detect(arquivo);
	}

	private String determineFileExtension(String mimeType) {
	    switch (mimeType) {
	        case MediaType.APPLICATION_PDF_VALUE:
	            return "pdf";
	        case MediaType.IMAGE_JPEG_VALUE:
	            return "jpeg";
	        case MediaType.IMAGE_PNG_VALUE:
	            return "png";
	        case MediaType.TEXT_PLAIN_VALUE:
	            return "txt";
	        default:
	            return "bin"; 
	    }   
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Checklist> update(@PathVariable Long id, @RequestBody Checklist obj){
//		obj = service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
}
