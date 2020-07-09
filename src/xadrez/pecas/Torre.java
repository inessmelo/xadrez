package xadrez.pecas;

import jogoTabuleiro.PosicaoJogo;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecasXadrez;

public class Torre extends PecasXadrez{
    
    public Torre(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);
    }
    
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        PosicaoJogo p = new PosicaoJogo(0, 0);
        
        //ACIMA
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
        while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //ABAIXO
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
        while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //ESQUERDA
        p.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
        while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIREITA
        p.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
        while(getTabuleiro().existePosicao(p) && !getTabuleiro().existePeca(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if(getTabuleiro().existePosicao(p) && existePecaOponente(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }        
        return matriz;
    }    
    
    @Override
    public String toString() {
        //return "T";
        return "\u2656"; // sem negrito
        //return "\u265c"; // em negrito
    }
}
