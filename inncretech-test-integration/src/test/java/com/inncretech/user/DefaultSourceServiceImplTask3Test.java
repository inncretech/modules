package com.inncretech.user;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.inncretech.core.sharding.IdGenerator;
import com.inncretech.core.test.TestUtil;
import com.inncretech.source.model.Source;
import com.inncretech.source.service.SourceService;
import com.inncretech.user.model.User;
import com.inncretech.user.model.UserProfile;
import com.inncretech.user.service.UserService;

//create 1 million users
//serial execution

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext-source.xml" })
public class DefaultSourceServiceImplTask3Test {

	 @Autowired
	private SourceService sourceService;
	 
	private long noOfSources = 1000;

	@Test
	  public void testSourceCreate(){
	    Source s = new Source();
	    for(int i=1;i<=noOfSources;i++){
	    	s.setSourceType(1);
	    	s.setSourceUri("testsource_"+i);		    
		    sourceService.create(s);
		     	
	    }
	     }



}
