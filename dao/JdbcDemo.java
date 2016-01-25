package com.gcit.training.lms.dao;

import java.util.*;

import com.gcit.training.lms.entity.*;
public class JdbcDemo {


	public static void main(String[] args) {
		
		PublisherDAO branch = new PublisherDAO();
		List<Publisher> branchList = null;

		try{
			List<Publisher> loaner1 = branch.readByPhone("907-222-2255");
			for(Publisher b : loaner1){
			System.out.println(b.getPublisherId()+": "+b.getPublisherName()+" , "+b.getPublisherAddress()+" , "+b.getPublisherPhone());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		

			

	}

}
