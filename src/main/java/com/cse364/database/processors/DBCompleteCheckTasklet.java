package com.cse364.database.processors;

import com.cse364.database.repositories.DBValidRepository;
import com.cse364.database.schemas.ValidSchema;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DBCompleteCheckTasklet implements Tasklet {
    DBValidRepository valid;

    public DBCompleteCheckTasklet(DBValidRepository valid, ApplicationContext applicationContext){
        this.valid = valid;
        this.applicationContext = applicationContext;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception{
        // Save valid bit
        valid.save(new ValidSchema(0));
        contribution.setExitStatus(ExitStatus.COMPLETED);
        System.out.println("[DB Loading Job] The job is COMPLETED!");
  
        return RepeatStatus.FINISHED;
    }
}
