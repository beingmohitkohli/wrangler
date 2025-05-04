/*
 * Copyright © 2025 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

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
