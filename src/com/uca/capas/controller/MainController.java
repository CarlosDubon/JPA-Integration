package com.uca.capas.controller;

import java.util.List;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		mav.setViewName("search");
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

}