package com.internal.im;

import junit.framework.Assert;

import org.junit.Test;

import com.im.ServiceLocator;
import com.im.service.ImService;

public class ImServiceTest extends BaseTest {
	private ImService imService = ServiceLocator.getService(ImService.class);

	@Test
	public void signUpUser() {
		String statusCode = imService.signUpUser("test", "test", "test@test.com");
		Assert.assertEquals("1", statusCode);
	}
	
}
