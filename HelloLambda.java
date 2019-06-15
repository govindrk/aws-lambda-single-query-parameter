package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class HelloLambda implements RequestHandler<UserInput, UserOutput> {

    @Override
    public UserOutput handleRequest(UserInput input, Context context) {
    	
    	UserOutput output = new UserOutput(); 
    	output.setMessage("Hello " + input.getName());
    	output.setFunctionName(context.getFunctionName());
    	output.setMemoryLimit(context.getMemoryLimitInMB());
        context.getLogger().log(input.getName() + " is excited to be using AWS Rest API");
        return output;
    }

}
