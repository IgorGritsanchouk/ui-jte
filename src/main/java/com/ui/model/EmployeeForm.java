package com.ui.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@NoArgsConstructor
@Data
public class EmployeeForm {

    private Map<String, String> countries;
    private Map<String, String> titles;
    private Map<String,String> regions;
}
