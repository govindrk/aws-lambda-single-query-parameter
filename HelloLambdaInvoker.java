package com.amazonaws.lambda.demo;

import java.nio.charset.StandardCharsets;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambdaAsyncClient;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.google.gson.Gson;

public class HelloLambdaInvoker {

	public static void main(String[] args) {
		String name = "Yamini Shamini";
		String region = "us-east-1";
		if (args.length > 0) {
			name = args[0];
		}
		if (args.length > 1) {
			region = args[1];
		}
		
		Gson gson = new Gson();
		@SuppressWarnings("deprecation")
		AWSLambdaAsyncClient client = new AWSLambdaAsyncClient(new ProfileCredentialsProvider("lambdaUser")).withRegion(Regions.fromName(region));
		UserInput in = new UserInput();
		in.setName(name);
		InvokeRequest request = new InvokeRequest().withFunctionName("helloLambda").withPayload(gson.toJson(in));
		InvokeResult result = client.invoke(request);
		String set = StandardCharsets.UTF_8.decode(result.getPayload()).toString();
		UserOutput output = gson.fromJson(set, UserOutput.class);
		System.out.println("Message : " + output.getMessage());
		System.out.println("Function : " + output.getFunctionName());
		System.out.println("Memory : " + output.getMemoryLimit());
	}
}
