package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 * Guia o agente de forma a fugir na direção contrária ao alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Chegar extends AlgoritmoMovimentacao {
    private int raio;
    private float timeToTarget;
    private static final char NOME = 'c';

    public Chegar(float maxVelocidade) {
        super(NOME);
        this.maxVelocidade = maxVelocidade;
        this.raio = 300;
        this.timeToTarget = .25f;
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();
        float x,y;
        x = agente.posicao.x - super.alvo.getObjetivo().x;
        y = agente.posicao.y - super.alvo.getObjetivo().y;
        Vector3 vel = new Vector3(x,y,0);
        output.velocidade = vel;
        if (output.velocidade.len() < raio){
            return output;
        }
        output.velocidade.scl(1/this.timeToTarget);
        if(output.velocidade.len() > maxVelocidade){
            output.velocidade.nor();
            output.velocidade.scl(maxVelocidade);
        }
        
        agente.olharNaDirecaoDaVelocidade(vel);
        output.rotacao = 0;
        
        // calcula que direção tomar (configura um objeto Direcionamento 
        // e o retorna)
        // ...
        // super.alvo já contém a posição do alvo
        // agente (parâmetro) é a pose do agente que estamos guiando
        // ...
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.C;
    }

}
