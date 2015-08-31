/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The http response we receive from parse server. Instances of this class are not immutable. The
 * response body may be consumed only once. The other fields are immutable.
 */
/** package */ class ParseHttpResponse {

  /**
   * Base builder for {@link ParseHttpResponse}.
   */
  /* package */ static abstract class Init<T extends Init<T>> {
    private int statusCode;
    private InputStream content;
    private long totalSize;
    private String reasonPhrase;
    private Map<String, String> headers;
    private String contentType;

    /* package */ abstract T self();

    public Init() {
      this.totalSize = -1;
      this.headers = new HashMap<>();
    }

    public T setStatusCode(int statusCode) {
      this.statusCode = statusCode;
      return self();
    }

    public T setContent(InputStream content) {
      this.content = content;
      return self();
    }

    public T setTotalSize(long totalSize) {
      this.totalSize = totalSize;
      return self();
    }

    public T setReasonPhrase(String reasonPhrase) {
      this.reasonPhrase = reasonPhrase;
      return self();
    }

    public T setHeaders(Map<String, String> headers) {
      this.headers = new HashMap<>(headers);
      return self();
    }

    public T addHeaders(Map<String, String> headers) {
      this.headers.putAll(headers);
      return self();
    }

    public T addHeader(String key, String value) {
      headers.put(key, value);
      return self();
    }

    public T setContentType(String contentType) {
      this.contentType = contentType;
      return self();
    }
  }

  /**
   * Builder of {@link ParseHttpResponse}.
   */
  public static class Builder extends Init<Builder> {

    @Override
    /* package */ Builder self() {
      return this;
    }

    public Builder() {
      super();
    }

    /**
     * Makes a new {@link ParseHttpResponse} {@code Builder} based on the input
     * {@link ParseHttpResponse}.
     *
     * @param response
     *          The {@link ParseHttpResponse} where the {@code Builder}'s values come from.
     */
    public Builder(ParseHttpResponse response) {
      super();
      this.setStatusCode(response.getStatusCode());
      this.setContent(response.getContent());
      this.setTotalSize(response.getTotalSize());
      this.setContentType(response.getContentType());
      this.setHeaders(response.getAllHeaders());
      this.setReasonPhrase(response.getReasonPhrase());
    }

    public ParseHttpResponse build() {
      return new ParseHttpResponse(this);
    }
  }

  private final int statusCode;
  private final InputStream content;
  private final long totalSize;
  private final String reasonPhrase;
  private final Map<String, String> headers;
  private final String contentType;

  /* package */ ParseHttpResponse(Init<?> builder) {
    this.statusCode = builder.statusCode;
    this.content = builder.content;
    this.totalSize = builder.totalSize;
    this.reasonPhrase = builder.reasonPhrase;
    this.headers = Collections.unmodifiableMap(new HashMap<>(builder.headers));
    this.contentType = builder.contentType;
  }

  /**
   * Generates a new {@link com.parse.ParseHttpResponse.Builder} based on an
   * {@link ParseHttpResponse}, the {@link com.parse.ParseHttpResponse.Builder}'s values are come
   * from the {@link ParseHttpResponse}.
   *
   * @return A new {@link com.parse.ParseHttpResponse.Builder} whose values are come from the
   * {@link ParseHttpResponse}.
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  public int getStatusCode() {
    return statusCode;
  }

  /**
   * Returns the content of the {@link ParseHttpResponse}'s body. The {@link InputStream} can only
   * be read once and can't be reset.
   *
   * @return The {@link InputStream} of the {@link ParseHttpResponse}'s body.
   */
  public InputStream getContent() {
    return content;
  }

  /**
   * Returns the size of the {@link ParseHttpResponse}'s body. -1 if the size of the
   * {@link ParseHttpResponse}'s body is unknown.
   *
   * @return The size of the {@link ParseHttpResponse}'s body.
   */
  public long getTotalSize() {
    return totalSize;
  }

  public String getReasonPhrase() {
    return reasonPhrase;
  }

  public String getContentType() {
    return contentType;
  }

  public String getHeader(String name) {
    return headers.get(name);
  }

  public Map<String, String> getAllHeaders() {
    return headers;
  }
}
