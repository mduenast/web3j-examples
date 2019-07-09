/*
 * Copyright 2019 mldu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.naluem.web3jexamples.gettingstarted;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

/**
 *
 * @author mldu
 */
public class StartSendingRequests {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		final String url = "https://morden.infura.io/your-token";
		final HttpService httpService = new HttpService(url);

		// To send synchronous requests:
		Web3j web3j = Web3j.build(httpService);
		Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
		String clientVersion = web3ClientVersion.getWeb3ClientVersion();

		//To send asynchronous requests using CompletableFuture
		web3ClientVersion=web3j.web3ClientVersion().sendAsync().get();
		clientVersion=web3ClientVersion.getWeb3ClientVersion();

		// To use RxJava Flowable
		web3j.web3ClientVersion().flowable().subscribe(x -> {
			String clientVersion1 = x.getWeb3ClientVersion();
			// ...
		});
		
		web3j.shutdown();
	}

}
