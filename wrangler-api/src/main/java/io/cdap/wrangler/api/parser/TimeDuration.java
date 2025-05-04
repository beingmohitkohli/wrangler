package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
  private final long millis;

  public TimeDuration(String value) {
    super(value);
    value = value.trim().toLowerCase();
    if (value.endsWith("ms"))
      millis = (long) Double.parseDouble(value.replace("ms", ""));
    else if (value.endsWith("s"))
      millis = (long) (Double.parseDouble(value.replace("s", "")) * 1000);
    else if (value.endsWith("min"))
      millis = (long) (Double.parseDouble(value.replace("min", "")) * 60_000);
    else
      millis = Long.parseLong(value);
  }

  public long getMillis() {
    return millis;
  }
}
