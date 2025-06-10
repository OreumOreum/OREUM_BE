package com.oreum.zzarit.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OreumException extends RuntimeException {
  private final HttpStatus status;

  public OreumException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public String statusCode() {
    return status.toString();
  }
}
