package dto.response;

public class ErrorResponseDTO {
    private String error;
    private String mensaje;

    public ErrorResponseDTO(String error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}