package jogoTabuleiro;

public class Tabuleiro {
    private Integer linhas;
    private Integer colunas;
    private PecasJogo[][] pecas;

    public Tabuleiro(Integer linhas, Integer colunas) {
        if (linhas < 1 || colunas < 1){
            throw  new TabuleiroExcecao("Erro criando tabuleiro: é necessário "
                    + "que haja pelo menos 1 linha e 1 coluna!");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new PecasJogo[linhas][colunas];
    }

    public Integer getLinhas() {
        return linhas;
    }

    public Integer getColunas() {
        return colunas;
    }

    public PecasJogo peca(Integer linha, Integer coluna){
        if (!existePosicao(linha, coluna)){
            throw new TabuleiroExcecao("Posição fora do tabuleiro!");
        }
        return pecas[linha][coluna];
    }
    
    public PecasJogo peca(PosicaoJogo posicao){
        if (!existePosicao(posicao)){
            throw new TabuleiroExcecao("Não existe posição no tabuleiro!");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    
    public void colocarPeca(PecasJogo peca, PosicaoJogo posicao){
        if(existePeca(posicao)){
           throw new TabuleiroExcecao("Já existe uma peça na posição " + posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }
    
    public PecasJogo removePeca(PosicaoJogo posicao){
        if(!existePosicao(posicao)){
            throw new TabuleiroExcecao("Não existe posição no tabuleiro!");
        }
        if (peca(posicao) == null){
            return null;
        }
        PecasJogo aux = peca(posicao);
        aux.posicao = null;
        pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return aux;
    }
    
    private boolean existePosicao(int linha, int coluna){
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }
    
    public boolean existePosicao(PosicaoJogo posicao){
        return existePosicao(posicao.getLinha(), posicao.getColuna());
    }
    
    public boolean existePeca(PosicaoJogo posicao){
        if (!existePosicao(posicao)){
            throw new TabuleiroExcecao("Não existe posição no tabuleiro!");
        }
        return peca(posicao) != null;
    }
}
