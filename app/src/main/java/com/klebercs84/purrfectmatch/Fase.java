package com.klebercs84.purrfectmatch;
import java.util.HashMap;
import java.util.Map;
public class Fase {

    private int numero;
    private int jogadasMax;
    private Map<Integer, Integer> objetivos; // tipo do gato -> quantidade necessária
    private Map<Integer, Integer> progresso; // tipo de gato -> quantidade coletada

    public Fase(int numero, int jogadasMax){
        this.numero = numero;
        this.jogadasMax = jogadasMax;
        this.objetivos = new HashMap<>();
        this.progresso = new HashMap<>();
    }

    // Adiciona um objetivo: coletar 'quantidade' gatos do 'tipo'
    public void adicionarObjetivo(int tipo, int quantidade){
        objetivos.put(tipo, quantidade);
        progresso.put(tipo, 0);
    }

    // Registra que um gato do tipo foi coletado
    public void registrarColeta(int tipo){
        if (!progresso.containsKey(tipo)) return;
        try {
            int atual = progresso.get(tipo);
            int meta = objetivos.get(tipo);
            if (atual < meta){
                progresso.put(tipo, atual +1);
            }
        } catch (Exception e){
            android.util.Log.e("Fase", "Erro ao registrar coleta: "+ e.getMessage());
        }
    }

    // Retorna o progresso total de 0.0 a 1.0
    public float getProgressoTotal(){
        if (objetivos.isEmpty()) return 0f;
        int totalMeta = 0;
        int totalColetado = 0;
        try {
            for (Map.Entry<Integer, Integer> entry : objetivos.entrySet()){
                totalMeta += entry.getValue();
                totalColetado += progresso.get(entry.getKey());
            }
            if (totalMeta == 0) return 0f;
            return (float) totalColetado/totalMeta;
        } catch (Exception e) {
            android.util.Log.e("Fase", "Erro ao calcular progresso: "+ e.getMessage());
            return 0f;
        }
    }

    // Verifica se todos os objetivos foram concluídos
    public boolean isConcluida(){
        return getProgressoTotal() >= 1.0f;
    }

    // Getters
    public int getNumero(){
        return numero;
    }

    public int getJogadasMax(){
        return jogadasMax;
    }

    public Map<Integer, Integer> getObjetivos(){
        return objetivos;
    }

    public Map<Integer,Integer> getProgresso(){
        return progresso;
    }
}
