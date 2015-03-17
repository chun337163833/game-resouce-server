package com.tmobile.web.freemarker.servlet;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.opensymphony.module.sitemesh.freemarker.FreemarkerDecoratorServlet;

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateModel;

@Configurable
public class SitemeshFreemarkerSpringServlet extends FreemarkerDecoratorServlet {
	
	@Autowired
	private FreeMarkerConfigurer configurer;
	
	@Override
	protected boolean preTemplateProcess(HttpServletRequest request,
			HttpServletResponse response, Template template,
			TemplateModel templateModel) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return super.preTemplateProcess(request, response, template, templateModel);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			this.injectSpringConfiguration(configurer.getConfiguration());
		} catch (Exception e) {
			throw new ServletException("Cannot inject freemarker configuration.", e);
		}
	}
	
	private void injectSpringConfiguration(Configuration config) throws Exception {
		Field f = FreemarkerServlet.class.getDeclaredField("config");
		try {
			f.setAccessible(true);
			f.set(this, config);
		} catch (Exception e) {
			throw e;
		} finally {
			f.setAccessible(false);
		}
		
		
	}
}
