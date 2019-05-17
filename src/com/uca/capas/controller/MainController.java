package com.uca.capas.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.StudentDAO;
import com.uca.capas.domain.Student;


@Controller
public class MainController {
	
	@Autowired
	private StudentDAO studentDao;
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		List<Student> students = studentDao.findAll();
		mav.addObject("students", students);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("/search")
	public ModelAndView search() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("search");
		return mav;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView insert(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("student",new Student());
		mav.setViewName("form");
		return mav;
	}
	@RequestMapping(value="/searchById",method=RequestMethod.POST)
	public ModelAndView getStudent(@RequestParam("id") Integer codigo) {
		ModelAndView mav = new ModelAndView();
		Student student = studentDao.findOne(codigo);
		mav.addObject("student", student);
		mav.setViewName("search");
		return mav;
	}
	
	@RequestMapping("/getAllStudents")
	public ModelAndView initMain(){
		
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
		 students = studentDao.findAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("students",students);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("/formData") 
	public ModelAndView save(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		int flag = 1;
		
		if(s.getcStudent()!=null) flag=0;
		
		try {
			studentDao.save(s, flag);
		}catch(Exception e) {
			System.out.println("Error no se agrego el estudiante " + e.getMessage());
		}
		students = studentDao.findAll();
		mav.addObject("students",students);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id")String id) {
		ModelAndView mav = new ModelAndView();
		Integer code = Integer.parseInt(id);
		Student s = studentDao.findOne(code);
		try {
			if(s!=null) {
				studentDao.delete(s);
			}
		}catch(DataAccessException ex) {
			System.out.print("No se pudo eliminar el estudiante");
		}
		List<Student> students = studentDao.findAll();
		mav.addObject("students", students);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id")String id) {
		System.out.println("Entro aqui");
		ModelAndView mav = new ModelAndView();
		Integer code = Integer.parseInt(id);
		Student student = studentDao.findOne(code);
		if(student != null) {
			mav.addObject("student", student);
		}else {
			mav.addObject("student", new Student());
		}
		mav.setViewName("form");
		return mav;
	}

}