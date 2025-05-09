package com.example.ms_ordenes.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RequestOrden {
    @NotNull(message = "Lista de productos no puede estar vacia")
    @Size(min = 1, message = "Debe contener al menos un producto para crear la orden")
    private List<Long> idProductos;

}
