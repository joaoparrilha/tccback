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
import com.devtcc.tccback.services.AtivoService;

@RestController
@RequestMapping(value = "/ativo")
@CrossOrigin(origins = "http://localhost:3000")
public class AtivoResource {
	
	@Autowired
	private AtivoService service;
	
	
	@GetMapping(value = "/usuario/{fk_usuario_id}")
	public ResponseEntity<List<Ativo>> findByusuarioId(@PathVariable Long fk_usuario_id) {
	 	List<Ativo> ativos = service.findByUsuario(fk_usuario_id);
		return ResponseEntity.ok().body(ativos);
	}
	
	@GetMapping("/dashboard")
	public ResponseEntity<List<Ativo>> findAll(){
		List<Ativo> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<List<Ativo>> findAllTrue(){
		List<Ativo> list = service.findValidacaoTrue();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Ativo> findById(@PathVariable Long id){
		Ativo usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}	
	
	@GetMapping("/validar")
	public ResponseEntity<List<Ativo>> findByValidacaoFalse(){
		List<Ativo> list = service.findValidacaoFalse();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Ativo> insert(
	        @RequestParam(value = "nome", required = false) String nome,
	        @RequestParam(value = "dominio", required = false) String dominio,
	        @RequestParam(value = "descricao", required = false) String descricao,
	        @RequestParam(value = "tipo", required = false) String tipo,
	        @RequestParam(value = "versao", required = false) String versao,
	        @RequestParam(value = "dependencia", required = false) String dependencia,
	        @RequestPart(value = "arquivo", required = false) MultipartFile file,
	        @RequestParam("fk_usuario_id") Long id) {

	    Ativo obj = new Ativo();  // Assuming 'Ativo' has a default constructor
	    obj.setNome(nome);
	    obj.setDominio(dominio);
	    obj.setDescricao(descricao);
	    obj.setTipo(tipo);
	    obj.setValidacao(false);
	    obj.setDependencia(dependencia);
	    obj.setDownload(0);

	    try {
	        obj.setVersao(Float.parseFloat(versao));

	        obj.setArquivo(file.getBytes());
	        
	        obj = service.insert(obj, id);
	        
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
	
//	@PutMapping(value = "/{id}")
//	public ResponseEntity<Ativo> update(@PathVariable Long id, @RequestBody Ativo obj){
//		obj = service.update(id, obj);
//		return ResponseEntity.ok().body(obj);
//	}
	
	
	@PutMapping(value = "/{id}", consumes = "multipart/form-data")
	public ResponseEntity<Ativo> update(
	        @PathVariable Long id,
	        @RequestParam(value = "nome", required = false) String nome,
	        @RequestParam(value = "dominio", required = false) String dominio,
	        @RequestParam(value = "descricao", required = false) String descricao,
	        @RequestParam(value = "tipo", required = false) String tipo,
	        @RequestParam(value = "versao", required = false) String versao,
	        @RequestParam(value = "dependencia", required = false) String dependencia,
	        @RequestPart(value = "arquivo", required = false) MultipartFile file) {

	    Ativo obj = service.findById(id); // Busca o objeto existente pelo ID
	    if (obj == null) {
	        return ResponseEntity.notFound().build(); // Retorna 404 se não encontrado
	    }

	    // Atualiza os campos do objeto com os dados recebidos
	    obj.setNome(nome);
	    obj.setDominio(dominio);
	    obj.setDescricao(descricao);
	    obj.setTipo(tipo);
	    obj.setValidacao(false);
	    obj.setDownload(0); // Mantenha essa lógica como desejar
	    obj.setDependencia(dependencia);
	    
	    try {
	        if (versao != null) {
	            obj.setVersao(Float.parseFloat(versao));
	        }

	        if (file != null && !file.isEmpty()) {
	            obj.setArquivo(file.getBytes()); // Armazena o arquivo
	        }

	        obj = service.update(id, obj); // Atualiza o objeto no banco de dados
	        return ResponseEntity.ok().body(obj); // Retorna o objeto atualizado
	    } catch (NumberFormatException e) {
	        return ResponseEntity.badRequest().body(null); // Retorna erro 400 para número inválido
	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).build(); // Retorna erro 500 em caso de falha de IO
	    }
	}
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam Long id) {
	    Ativo ativo = service.findById(id);
	    if (ativo == null || ativo.getArquivo() == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    ativo.setDownload(ativo.getDownload() + 1);
	    ativo = service.update(id, ativo);
	    
	    ByteArrayResource resource = new ByteArrayResource(ativo.getArquivo());

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
	        default:
	            return "bin"; 
	    }   
	}

}
