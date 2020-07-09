package xadrez.pecas;

import jogoTabuleiro.PosicaoJogo;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecasXadrez;

public class Rei extends PecasXadrez{
    
    public Rei(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);
    }

    private boolean podeMover(PosicaoJogo posicao){
        PecasXadrez p = (PecasXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }
    
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        PosicaoJogo p = new PosicaoJogo(0, 0);
        
        //ACIMA
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //ABAIXO
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //ESQUERDA
        p.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIREITA
        p.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //ESQUERDA ACIMA
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //ESQUERDA ABAIXO
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIREITA ACIMA
        p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        //DIREITA ABAIXO
        p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if(getTabuleiro().existePosicao(p) && podeMover(p)){
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        
        return matriz;
    }
    
    @Override
    public String toString() {
        return "K";
        //return "\u2654"; //sem negrito
        //return "\u265a";  //em negrito
    }
}
