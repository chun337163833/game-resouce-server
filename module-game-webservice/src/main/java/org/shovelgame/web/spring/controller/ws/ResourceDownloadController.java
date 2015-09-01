package org.shovelgame.web.spring.controller.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.shovelgame.annotation.Logger;
import org.shovelgame.game.domain.data.Texture;
import org.shovelgame.game.domain.data.TextureGroup;
import org.shovelgame.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resources/")
@Logger
public class ResourceDownloadController {

	@Value("${bundles.file.path}")
	public String bundlesPath;
	
	@Value("${bundles.states}")
	public String[] bundlesStates;
	
	@RequestMapping("groups")
	public List<String> loadTextureGroups() {
		List<TextureGroup> groups = TextureGroup.findAllTextureGroups();
		List<String> names = new ArrayList<>();
		for(TextureGroup group: groups) {
			names.add(group.getId());
		}
		return names;
	}
	
	@RequestMapping("sync/groups/{version}")
	public List<String> syncGroup(@PathVariable("version") BigDecimal version) {
		List<TextureGroup> textures = TextureGroup.findTexturGroupsGreaterThenVersionGroupByTextureGroup(version);
		List<String> groups = new ArrayList<>();
		for(TextureGroup g: textures) {
			groups.add(g.getId());
		}
		return groups;
	}
	
	@RequestMapping("sync/states/{version}")
	public String[] getStates(@PathVariable("version") BigDecimal version) {
		return this.bundlesStates;
	}
	
	@RequestMapping("sync/textures/{group}/{version}")
	public ImageBundles syncTexturesByGroup(@PathVariable("group") String group, @PathVariable("version") BigDecimal version) {
		ImageBundles bundles = new ImageBundles();
		List<Texture> textures = Texture.findTexturesGreaterThenVersion(version, group);
		ImageBundle[] ib = new ImageBundle[textures.size()];
		for(int i = 0; i < textures.size(); i++) {
			Texture t = textures.get(i);
			ImageBundle b = new ImageBundle();
			b.value = t.getId();
			b.data = loadProperties(group, b.value);
			ib[i] = b;
		}
		bundles.bundles = ib;
//		bundles.states = bundlesStates;
		return bundles;
	}
	
	private Map<String, String> loadProperties(String group, String bundle) {
		String path = bundlesPath + group + File.separator + bundle;
		Properties p = new Properties();
		Map<String, String> data = new HashMap<>();
		File stateData = new File(path + File.separator + bundle + ".data");
		if(stateData.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(stateData);
				p.load(fis);
				for(Map.Entry<Object, Object> entry: p.entrySet()) {
					data.put(entry.getKey().toString(), entry.getValue().toString());
				}
			} catch (Exception e) {
				log.error("", e);
			} finally {
				 IOUtils.closeQuietly(fis);
			}
			
			
		}
		return data;
	}
	
	@RequestMapping("textures/{group}/{bundle}")
	public String[] getAllImagesInSubfolder(@PathVariable("group") String group, @PathVariable("bundle") String bundle, @RequestParam(value="state", required=false) String state) {
		String path = bundlesPath + group + File.separator + bundle;
		//if state is specified then rewrite context path and load additional properties
		if(!StringUtils.isEmpty(state)) {
			path = path + File.separator + state;
		}
		//add all images names in folder to array list
		List<String> files = new ArrayList<>();
		File file = new File(path);
		if(file.isDirectory()) {
			for(String s: file.list()) {
				files.add(s);
			}
		}
		return files.toArray(new String[files.size()]);
	}
	
	@RequestMapping("textures/**")
	public ResponseEntity<byte[]> getImage(HttpServletRequest request) {
		String wait = request.getParameter("wait");
		long waitTime = 0;
		if(!StringUtils.isEmpty(wait)) {
			waitTime = Long.valueOf(wait);
		}
		String path = request.getRequestURI();
		path = path.replace(request.getContextPath(), "").replace(request.getServletPath(), "");
		path = path.replace("/resources/textures/", "");
		File file = new File(bundlesPath + path);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			final HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_PNG);
			Thread.sleep(waitTime);
			return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
		} catch (Exception e) {
			log.error("", e);
			return null;
		} finally {
			IOUtils.closeQuietly(in);
		}
	   
	}
	public class ImageBundle {
		private String value;
		private Map<String, String> data = new HashMap<>();
		public Map<String, String> getData() {
			return data;
		}
		public String getValue() {
			return value;
		}
	}
	
	public class ImageBundles {
		private ImageBundle[] bundles;
		public ImageBundle[] getBundles() {
			return bundles;
		}
	}
	
}
