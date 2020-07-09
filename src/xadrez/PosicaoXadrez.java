package xadrez;

import jogoTabuleiro.PosicaoJogo;

public class PosicaoXadrez {
    private Character coluna;
    private Integer linha;

    public PosicaoXadrez(Character coluna, Integer linha) {
        if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8){
            throw new XadrezExcecao("Erro instanciando a Exceção: valores "
                    + "válidos de a1 até h8!");
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public Character getColuna() {
        return coluna;
    }

    public Integer getLinha() {
        return linha;
    }
    
    protected PosicaoJogo posicionar(){
        return new PosicaoJogo(8 - linha, coluna - 'a');
    }
    
    protected static PosicaoXadrez paraPosicao(PosicaoJogo posicao){
        return new PosicaoXadrez((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
    }

    @Override
    public String toString() {
        return coluna + "" + linha;
    }
    
    
}
