package com.funix.lab.prj321x_asm3_tamndfx27974.service;

import com.funix.lab.prj321x_asm3_tamndfx27974.dto.HttpResponse;

import java.util.List;
import java.util.Map;

public interface SearchService {

    HttpResponse<Map<String, List<?>>> searchCommon(String keyword);

    HttpResponse<?> searchSpecialization(String keyword);
}
