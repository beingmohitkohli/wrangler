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

import io.cdap.wrangler.api.parser.Token;

public class ByteSize extends Token {
  private final long bytes;

  public ByteSize(String value) {
    super(value);
    value = value.trim().toUpperCase();
    if (value.endsWith("KB"))
      bytes = (long)(Double.parseDouble(value.replace("KB", "")) * 1024);
    else if (value.endsWith("MB"))
      bytes = (long)(Double.parseDouble(value.replace("MB", "")) * 1024 * 1024);
    else if (value.endsWith("GB"))
      bytes = (long)(Double.parseDouble(value.replace("GB", "")) * 1024 * 1024 * 1024);
    else
      bytes = Long.parseLong(value);
  }

  public long getBytes() {
    return bytes;
  }
}
