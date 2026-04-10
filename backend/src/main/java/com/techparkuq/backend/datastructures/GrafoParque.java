package com.techparkuq.backend.datastructures;

import java.util.*;

public class GrafoParque {

    private final Map<Long, Vertice> vertices;

    public GrafoParque() {
        this.vertices = new HashMap<>();
    }

    public void agregarVertice(Long atraccionId) {
        vertices.putIfAbsent(atraccionId, new Vertice(atraccionId));
    }

    public void agregarArista(Long origenId, Long destinoId, int peso) {
        agregarVertice(origenId);
        agregarVertice(destinoId);

        vertices.get(origenId).agregarArista(new Arista(destinoId, peso));
        vertices.get(destinoId).agregarArista(new Arista(origenId, peso));
    }

    public List<Long> dijkstra(Long origenId, Long destinoId) {
        Map<Long, Integer> distancias = new HashMap<>();
        Map<Long, Long> anteriores = new HashMap<>();
        PriorityQueue<long[]> cola = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));

        for (Long verticeId : vertices.keySet()) {
            distancias.put(verticeId, Integer.MAX_VALUE);
        }

        distancias.put(origenId, 0);
        cola.offer(new long[]{origenId, 0});

        while (!cola.isEmpty()) {
            long[] actual = cola.poll();
            Long actualId = actual[0];
            int distanciaActual = (int) actual[1];

            if (distanciaActual > distancias.get(actualId)) {
                continue;
            }

            if (actualId.equals(destinoId)) {
                break;
            }

            for (Arista arista : vertices.get(actualId).getAdyacentes()) {
                Long vecinoId = arista.getDestinoId();
                int nuevaDistancia = distancias.get(actualId) + arista.getPeso();

                if (nuevaDistancia < distancias.get(vecinoId)) {
                    distancias.put(vecinoId, nuevaDistancia);
                    anteriores.put(vecinoId, actualId);
                    cola.offer(new long[]{vecinoId, nuevaDistancia});
                }
            }
        }

        return reconstruirRuta(anteriores, origenId, destinoId);
    }

    public int calcularDistanciaRuta(List<Long> ruta) {
        int total = 0;

        for (int i = 0; i < ruta.size() - 1; i++) {
            Long actual = ruta.get(i);
            Long siguiente = ruta.get(i + 1);

            for (Arista arista : vertices.get(actual).getAdyacentes()) {
                if (arista.getDestinoId().equals(siguiente)) {
                    total += arista.getPeso();
                    break;
                }
            }
        }

        return total;
    }

    private List<Long> reconstruirRuta(Map<Long, Long> anteriores, Long origenId, Long destinoId) {
        List<Long> ruta = new ArrayList<>();

        if (!origenId.equals(destinoId) && !anteriores.containsKey(destinoId)) {
            return ruta;
        }

        Long actual = destinoId;
        while (actual != null) {
            ruta.add(actual);
            actual = anteriores.get(actual);
        }

        Collections.reverse(ruta);
        return ruta;
    }
}