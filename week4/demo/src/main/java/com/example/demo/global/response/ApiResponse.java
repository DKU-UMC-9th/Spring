
package com.example.demo.global.response;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {

    private final boolean success; // 성공 여부
    private final String code;     // 비즈니스 코드 (예: R001)
    private final String message;  // 기본 메시지
    private final T data;          // 실제 데이터 (ReviewResponse, List<ReviewDto> 등)

    private ApiResponse(boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    // ========= 성공 응답용 정적 메서드 =========

    // data 있는 경우
    public static <T> ResponseEntity<ApiResponse<T>> success(SuccessCode code, T data) {
        ApiResponse<T> body = new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                data
        );
        return ResponseEntity.status(code.getHttpStatus()).body(body);
    }

    // data 없는 경우 (DELETE 등)
    public static ResponseEntity<ApiResponse<Void>> success(SuccessCode code) {
        ApiResponse<Void> body = new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                null
        );
        return ResponseEntity.status(code.getHttpStatus()).body(body);
    }
}
