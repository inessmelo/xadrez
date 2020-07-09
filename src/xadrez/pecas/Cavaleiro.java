package xadrez.pecas;

import jogoTabuleiro.PosicaoJogo;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecasXadrez;

public class Cavaleiro extends PecasXadrez{

    public Cavaleiro(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);
    }
    
    public boolean podeMover(PosicaoJogo posicao){
        PecasXadrez p = (PecasXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }
    
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        PosicaoJogo p = new PosicaoJogo(0, 0);
        
        //2 ACIMA - 1 ESQUERDO
        p.definirValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ACIMA = 1 DIREITA
        p.definirValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ABAIXO - 1 ESQUERDA
        p.definirValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ABAIXO - 1 DIREITA
        p.definirValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ESQUERDA - 1 ACIMA
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 ESQUERDA - 1 ABAIXO
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 DIREITA - 1 ACIMA
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //2 DIREITA - 1 ABAIXO
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        return matriz;
    }
    
    @Override
    public String toString(){
        return "\u2658";
        //CAVALO PRETO
        //return "\u265e";

    }
}
