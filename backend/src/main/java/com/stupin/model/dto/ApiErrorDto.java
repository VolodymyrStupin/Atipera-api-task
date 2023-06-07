package com.stupin.model.dto;

public class ApiErrorDto {
	private int statusCode;
	private String errorMessage;

	public ApiErrorDto() {
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ApiErrorDto{" +
			"statusCode=" + statusCode +
			", errorMessage='" + errorMessage + '\'' +
			'}';
	}

	public static final class Builder {
		private int statusCode;
		private String errorMessage;

		public Builder() {
		}

		public Builder withStatusCode(final int statusCode) {
			this.statusCode = statusCode;
			return this;
		}

		public Builder withErrorMessage(final String errorMessage) {
			this.errorMessage = errorMessage;
			return this;
		}

		public ApiErrorDto build() {
			final ApiErrorDto result = new ApiErrorDto();
			result.setStatusCode(this.statusCode);
			result.setErrorMessage(this.errorMessage);
			return  result;
		}
	}
}
