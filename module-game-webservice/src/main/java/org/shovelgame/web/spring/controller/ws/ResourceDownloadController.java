package org.shovelgame.web.spring.controller.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.shovelgame.annotation.Logger;
import org.shovelgame.game.domain.model.MinionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources/")
@Logger
public class ResourceDownloadController {

	@Value("${bundles.file.path}")
	public String bundlesPath;
	
	@Value("${bundles.states}")
	public String[] bundlesStates;
	
	@RequestMapping("bundleNames")
	public ImageBundles getAllImageBundleRootNames() {
		ImageBundles bundles = new ImageBundles();
		List<MinionModel> models = MinionModel.findAllMinionModels();
		Set<String> n = new HashSet<>();
		for(int i = 0; i < models.size(); i++) {
			n.add(models.get(i).getImageBundleName());
		}
		bundles.bundles = n.toArray(new String[n.size()]);
		bundles.states = bundlesStates;
		return bundles;
	}
	
	@RequestMapping("textures/{bundle}/{state}")
	public List<String> getAllImagesInSubfolder(@PathVariable("bundle") String bundle, @PathVariable("state") String state) {
		List<String> files = new ArrayList<>();
		File file = new File(bundlesPath + bundle + File.separator + state);
		if(file.isDirectory()) {
			for(String s: file.list()) {
				files.add(s);
			}
		}
		return files;
	}
	
	@RequestMapping("textures/**")
	public ResponseEntity<byte[]> getImage(HttpServletRequest request) {
		String path = request.getRequestURI();
		path = path.replace(request.getContextPath(), "").replace(request.getServletPath(), "");
		path = path.replace("/resources/textures/", "");
		File file = new File(bundlesPath + path);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			 final HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.IMAGE_PNG);

			    return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("", e);
			return null;
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("", e);
				}
			}
		}
	   
	}
	
	public class ImageBundles {
		private String[] bundles;
		private String[] states;
		public String[] getStates() {
			return states;
		}
		public String[] getBundles() {
			return bundles;
		}
	}
	
}
