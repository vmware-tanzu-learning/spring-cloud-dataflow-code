/*
 * Copyright 2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.spring.batchlab.configuration;

import io.spring.batchlab.BatchLabProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@EnableTask
public class BatchDemoConfiguration {

	@Autowired
	BatchLabProperties batchLabProperties;

	private static final Log logger = LogFactory.getLog(BatchDemoConfiguration.class);


	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job1() {
		SimpleJobBuilder jobBuilder =  jobBuilderFactory.get(batchLabProperties.getBatchName())
				.start(stepBuilderFactory.get("job1stepX")
						.tasklet(new Tasklet() {
							@Override
							public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
								logger.info("Job1 is sleeping for 30 seconds");
								Thread.sleep(30000);
								logger.info("Job1 was finishing");
								return RepeatStatus.FINISHED;
							}
						})
						.build()).incrementer(new RunIdIncrementer());

					return jobBuilder.build();
	}

}
