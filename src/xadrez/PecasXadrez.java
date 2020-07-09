package xadrez;

import jogoTabuleiro.PecasJogo;
import jogoTabuleiro.PosicaoJogo;
import jogoTabuleiro.Tabuleiro;

public abstract class PecasXadrez extends PecasJogo{
    private Cores cor;
    private Integer contarMovimento;

    public PecasXadrez(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cores getCor() {
        return cor;
    }

    public Integer getContarMovimento() {
        return contarMovimento;
    }
        
    public void encrementaContagemMovimento(){
        contarMovimento++;
    }
    
    public void decrementaContagemMovimento(){
        contarMovimento--;
    }
    
    public PosicaoXadrez peguePosicaoXadrez(){
        return PosicaoXadrez.paraPosicao(posicao);
    }
    
    protected boolean existePecaOponente(PosicaoJogo posicao){
        PecasXadrez p = (PecasXadrez)getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }

}
