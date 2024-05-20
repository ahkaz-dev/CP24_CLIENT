package ru.harmony.cp24_client.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse<T> extends BaseResponse {
    private List<T> data;

    public ListResponse(boolean status, String status_text, List<T> data) {
        super(status, status_text);
        this.data = data;
    }
}
