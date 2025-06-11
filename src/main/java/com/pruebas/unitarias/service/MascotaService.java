package com.pruebas.unitarias.service;

import com.pruebas.unitarias.model.Mascota;
import com.pruebas.unitarias.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    @Autowired
    private MascotaRepository mascotaRepository;

    public Mascota guardarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    public Mascota actualizarMascota(Long id, Mascota mascota) {
        Mascota existente = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe la mascota"));
        existente.setNombre(mascota.getNombre());
        existente.setTipo(mascota.getTipo());
        existente.setEdad(mascota.getEdad());
        return mascotaRepository.save(existente);
    }

    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }

    public Mascota editarMascota(Mascota mascota) {
        Optional<Mascota> existingMascotaOptional = mascotaRepository.findById(mascota.getId());

        if (existingMascotaOptional.isPresent()) {
            Mascota existingMascota = existingMascotaOptional.get();
            existingMascota.setNombre(mascota.getNombre());
            existingMascota.setTipo(mascota.getTipo());
            existingMascota.setEdad(mascota.getEdad());
            return mascotaRepository.save(existingMascota);
        } else {
            return null; 
        }
    }
}