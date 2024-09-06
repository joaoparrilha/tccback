package com.devtcc.tccback.resources;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.apache.tika.Tika;
import com.devtcc.tccback.entities.Ativo;
import com.devtcc.tccback.services.AtivoService;

@RestController
@RequestMapping(value = "/ativo")
@CrossOrigin(origins = "http://localhost:3000")
public class AtivoResource {
	
	@Autowired
	private AtivoService service;
	
	@GetMapping
	public ResponseEntity<List<Ativo>> findAll(){
		List<Ativo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ativo> findById(@PathVariable Long id){
		Ativo usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}	
	
	@GetMapping("/validar")
	public ResponseEntity<List<Ativo>> findByValidacaoFalse(){
		List<Ativo> list = service.findValidacao();
		return ResponseEntity.ok().body(list);
	}
	
	//@PostMapping
	//public ResponseEntity<Ativo> insert(@RequestBody Ativo obj){
	//	obj = service.insert(obj);
	//	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	//	return ResponseEntity.created(uri).body(obj);
	//}
	
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Ativo> insert(
	        @RequestParam("nome") String nome,
	        @RequestParam("dominio") String dominio,
	        @RequestParam("descricao") String descricao,
	        @RequestParam("tipo") String tipo,
	        @RequestParam("versao") String versao,  // 'versao' will be converted to Float
	        @RequestPart("arquivo") MultipartFile file) {

	    Ativo obj = new Ativo();  // Assuming 'Ativo' has a default constructor
	    obj.setNome(nome);
	    obj.setDominio(dominio);
	    obj.setDescricao(descricao);
	    obj.setTipo(tipo);

	    try {
	        // Convert the 'versao' from String to Float
	        obj.setVersao(Float.parseFloat(versao));

	        // Set the file as byte array
	        obj.setArquivo(file.getBytes());
	        
	        // Insert the object using the service
	        obj = service.insert(obj);
	        
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).body(obj);
	    } catch (NumberFormatException e) {
	        // Handle the exception if 'versao' is not a valid float
	        return ResponseEntity.badRequest().body(null);
	    } catch (IOException e) {
	        // Handle the exception for file upload issues
	        e.printStackTrace();
	        return ResponseEntity.status(500).build();
	    }
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Ativo> update(@PathVariable Long id, @RequestBody Ativo obj){
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam Long id) {
	    Ativo ativo = service.findById(id);
	    if (ativo == null || ativo.getArquivo() == null) {
	        return ResponseEntity.notFound().build();
	    }

	    ByteArrayResource resource = new ByteArrayResource(ativo.getArquivo());

	    // Determina o tipo MIME com base no conteúdo do arquivo
	    String mimeType = determineMimeType(ativo.getArquivo());
	    String fileExtension = determineFileExtension(mimeType);

	    HttpHeaders headers = new HttpHeaders();
	    headers.add(HttpHeaders.CONTENT_TYPE, mimeType != null ? mimeType : MediaType.APPLICATION_OCTET_STREAM_VALUE);
	    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ativo.getNome() + "." + fileExtension + "\"");

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
	        case "application/sql":
	            return "sql";
	        // Adicione mais tipos conforme necessário
	        default:
	            return "bin";  // Extensão genérica para tipos MIME desconhecidos
	    }   
	}

	
	/*	@GetMapping("/download")
	public ResponseEntity<byte[]> baixarArquivo(@RequestParam Long id) {
	    try {
	        Ativo ativo = service.findById(id);

	        byte[] arquivo = ativo.getArquivo(); 

	        return ResponseEntity.ok()
	                .header("Content-Disposition", "attachment; filename=\"arquivo_" + id + "\"")
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(arquivo);
	    } catch (Exception e) {
	        return ResponseEntity.status(500).build();
	    }
	}
*/
}
