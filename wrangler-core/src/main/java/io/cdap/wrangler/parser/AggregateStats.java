package io.cdap.wrangler.parser.recipe.directives;

import io.cdap.wrangler.api.Arguments;
import io.cdap.wrangler.api.Directive;
import io.cdap.wrangler.api.ExecutorContext;
import io.cdap.wrangler.api.Row;
import io.cdap.wrangler.api.parser.ColumnName;
import io.cdap.wrangler.api.UsageDefinition;
import io.cdap.wrangler.api.parser.ByteSize;
import io.cdap.wrangler.api.parser.TimeDuration;

import java.util.Collections;
import java.util.List;

public class AggregateStats implements Directive {
  private String sizeColumn, timeColumn, outputSize, outputTime;
  private long totalBytes = 0;
  private long totalMillis = 0;

  @Override
  public UsageDefinition define() {
    return UsageDefinition.builder("aggregate-stats")
        .addRequiredArg("sizeColumn")
        .addRequiredArg("timeColumn")
        .addRequiredArg("outputSize")
        .addRequiredArg("outputTime")
        .build();
  }

  @Override
  public void initialize(Arguments args) {
    sizeColumn = ((ColumnName) args.value("sizeColumn")).value();
    timeColumn = ((ColumnName) args.value("timeColumn")).value();
    outputSize = ((ColumnName) args.value("outputSize")).value();
    outputTime = ((ColumnName) args.value("outputTime")).value();
  }

  @Override
  public List<Row> execute(List<Row> rows, ExecutorContext context) {
    for (Row row : rows) {
      Object size = row.getValue(sizeColumn);
      Object time = row.getValue(timeColumn);
      ByteSize bs = new ByteSize(size.toString());
      TimeDuration td = new TimeDuration(time.toString());
      totalBytes += bs.getBytes();
      totalMillis += td.getMillis();
    }

    Row output = new Row();
    output.add(outputSize, totalBytes / (1024.0 * 1024.0)); // MB
    output.add(outputTime, totalMillis / 1000.0); // Seconds
    return Collections.singletonList(output);
  }
}
