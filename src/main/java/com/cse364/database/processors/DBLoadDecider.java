package com.cse364.database.processors;

import com.cse364.database.repositories.DBValidRepository;
import com.cse364.database.schemas.ValidSchema;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class DBLoadDecider implements JobExecutionDecider {
    DBValidRepository valid;

    public DBLoadDecider(DBValidRepository valid){
        this.valid = valid;
    }

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        if(!valid.existsById(0)) {
            System.out.println("-------------------------------- Database Loading Start --------------------------------");
            System.out.println("[DB Loading Job] DB Loading job takes about 15 minute.");
            System.out.println("[DB Loading Job] If you insert a request while the job is running, the job will crash.");
            return new FlowExecutionStatus("NOT_LOADED");
        } else {
            System.out.println("-------------------------------- Start Service --------------------------------");
            return new FlowExecutionStatus("LOADED");
        }


    }
}
