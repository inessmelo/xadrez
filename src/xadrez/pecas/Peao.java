package xadrez.pecas;

import jogoTabuleiro.PosicaoJogo;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cores;
import xadrez.PecasXadrez;

public class Peao extends PecasXadrez{

    public Peao(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);
    }
    
    @Override
    public boolean[][] possiveisMovimentos() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        PosicaoJogo p1 = new PosicaoJogo(0, 0);
        //PosicaoJogo p2 = new PosicaoJogo(posicao.getLinha() - 1, posicao.getColuna());
        
        if(getCor() == Cores.BRANCO){
            //1 CASA ACIMA            
            p1.definirValores(posicao.getLinha() - 1, posicao.getColuna());
            if(getTabuleiro().existePosicao(p1) && !getTabuleiro().existePeca(p1)){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }
            /*
            //2 CASAS ACIMA (ERRO)            
            p1.definirValores(posicao.getLinha() - 2, posicao.getColuna());
            PosicaoJogo p2 = new PosicaoJogo(posicao.getLinha() - 1, posicao.getColuna());
            if(getTabuleiro().existePosicao(p1) && !getTabuleiro().existePeca(p1) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContarMovimento() == 0){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }            
            */  
            //CASA ESQUERDA ACIMA
            p1.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if(getTabuleiro().existePosicao(p1) && existePecaOponente(p1)){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }
            //CASA DIREITA ACIMA
            p1.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if(getTabuleiro().existePosicao(p1) && existePecaOponente(p1)){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }
        }
        
        else{
            //1 CASA ABAIXO
            p1.definirValores(posicao.getLinha() + 1, posicao.getColuna());
            if(getTabuleiro().existePosicao(p1) && !getTabuleiro().existePeca(p1)){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }
            //2 CASAS ABAIXO (ERRO)
            /*p1.definirValores(posicao.getLinha() + 2, posicao.getColuna());
            PosicaoJogo p2 = new PosicaoJogo(posicao.getLinha() + 1, posicao.getColuna());
            if(getTabuleiro().existePosicao(p1) && !getTabuleiro().existePeca(p1) && getTabuleiro().existePosicao(p2) && !getTabuleiro().existePeca(p2) && getContarMovimento() == 0){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }*/
            //CASA ESQUERDA ABAIXO
            p1.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if(getTabuleiro().existePosicao(p1) && existePecaOponente(p1)){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }
            //CASA DIREITA ABAIXO
            p1.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if(getTabuleiro().existePosicao(p1) && existePecaOponente(p1)){
                matriz[p1.getLinha()][p1.getColuna()] = true;
            }
        }        
        return matriz;
    }
    
    @Override
    public String toString(){
        return "P";
        //return "\u2659"; //sem negrito
        //return "\u265f";  //negrito
    }
}
