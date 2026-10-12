
package task_manager_api.response;

import java.util.List;

public class ApiErrorResponse {

    private int status;
    private String erro;
    private List<String> mensagens;

    public ApiErrorResponse(int status, String erro, List<String> mensagens) {
        this.status = status;
        this.erro = erro;
        this.mensagens = mensagens;
    }

    public int getStatus() {
        return status;
    }

    public String getErro() {
        return erro;
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}
