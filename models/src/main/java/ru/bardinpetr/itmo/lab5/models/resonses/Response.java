package ru.bardinpetr.itmo.lab5.models.resonses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private boolean success = true;
    private String error = null;
}
