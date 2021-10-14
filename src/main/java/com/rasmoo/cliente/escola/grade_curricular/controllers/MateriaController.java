package com.rasmoo.cliente.escola.grade_curricular.controllers;

import com.rasmoo.cliente.escola.grade_curricular.exceptions.MateriaNotFoundException;
import com.rasmoo.cliente.escola.grade_curricular.exceptions.SendIdException;
import com.rasmoo.cliente.escola.grade_curricular.models.dto.MateriaDTO;
import com.rasmoo.cliente.escola.grade_curricular.models.dto.MessageResponseDTO;
import com.rasmoo.cliente.escola.grade_curricular.models.dto.ResponseDTO;
import com.rasmoo.cliente.escola.grade_curricular.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    private MateriaService materiaService;

    @Autowired
    MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseDTO<MateriaDTO> listMaterias () {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(materiaService.listMaterias());
        responseDTO.setHttpStatus(HttpStatus.OK.value());

        return responseDTO;

    }

    @GetMapping("{id}")
    public ResponseDTO<MateriaDTO> getMateriaById(@PathVariable Long id) throws MateriaNotFoundException {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(materiaService.getMateriaById(id));
        responseDTO.setHttpStatus(HttpStatus.OK.value());

        return responseDTO;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<MessageResponseDTO> createMateria(@Valid @RequestBody MateriaDTO materiaDTO) throws SendIdException {

        if(materiaDTO.getId() != null)
            throw new SendIdException("materia");

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(materiaService.createMateria(materiaDTO));
        responseDTO.setHttpStatus(HttpStatus.CREATED.value());

        return responseDTO;

    }

    @PutMapping("{id}")
    public ResponseDTO<MessageResponseDTO> update(@Valid @PathVariable Long id, @RequestBody MateriaDTO materiaDTO) throws MateriaNotFoundException {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(materiaService.updateMateria(id, materiaDTO));
        responseDTO.setHttpStatus(HttpStatus.OK.value());

        return responseDTO;

    }

    @DeleteMapping("{id}")
    public ResponseDTO<MessageResponseDTO> delete(@PathVariable Long id) throws MateriaNotFoundException {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(materiaService.deleteMateriaById(id));
        responseDTO.setHttpStatus(HttpStatus.OK.value());

        return responseDTO;

    }

}
