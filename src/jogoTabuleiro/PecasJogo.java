package jogoTabuleiro;

public abstract class PecasJogo {
    protected PosicaoJogo posicao;
    private Tabuleiro tabuleiro;

    public PecasJogo(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }
    
    public abstract boolean [][] possiveisMovimentos();
    
    public boolean possiveisMovimentos(PosicaoJogo posicao){
        return possiveisMovimentos()[posicao.getLinha()][posicao.getColuna()];
        
    }
    
    public boolean existeUmPossivelMovimento(){
        boolean[][] matriz = possiveisMovimentos();
        for(int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz.length; j++){
                if (matriz[i][j]){
                    return true;
                }
            }
        }
        return  false;
    }
}
