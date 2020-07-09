package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jogoTabuleiro.PecasJogo;
import jogoTabuleiro.PosicaoJogo;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavaleiro;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {
    private Integer virar;
    private Cores jogadorAtual;
    private Tabuleiro tabuleiro;
    private boolean check;
    private boolean checkMate;
    
    private List<PecasJogo> pecasNoTabuleiro = new ArrayList<>();
    private List<PecasJogo> pecasCapturada = new ArrayList<>();
    
    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8, 8);
        virar = 1;
        jogadorAtual = Cores.BRANCO;
        configuracaoInicial();
    }

    public Integer getVirar() {
        return virar;
    }

    public Cores getJogadorAtual() {
        return jogadorAtual;
    }
    
    public boolean getCheck(){
        return check;
    }
    
    public boolean getCheckMate(){
        return checkMate;
    }
    
    public PecasXadrez[][] pegarPecas(){
        PecasXadrez[][] matriz = new PecasXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++){
            for (int j = 0; j < tabuleiro.getColunas(); j++){
                matriz[i][j] = (PecasXadrez)tabuleiro.peca(i, j);
            }
        }
        return matriz;
    }
    
    public boolean [][] possiveisMovimentos(PosicaoXadrez posicaoOrigem){
        PosicaoJogo posicao = posicaoOrigem.posicionar();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).possiveisMovimentos();
    }
    
    public PecasXadrez executarMovimentoXadrez(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino){
        PosicaoJogo origem = posicaoOrigem.posicionar();
        PosicaoJogo destino = posicaoDestino.posicionar();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        PecasJogo capturaPeca = fazMovimento(origem, destino);
        
        if(testarCheck(jogadorAtual)){
            desfazerMovimento(origem, destino, capturaPeca);
            throw new XadrezExcecao("Você não pode se colocar em CHECK!");
        }
        check = (testarCheck(oponente(jogadorAtual)))? true : false;
        if(testarCheckMate(oponente(jogadorAtual))){
            checkMate = true;
        }
        else{
            proximoVirar();
        }
        return (PecasXadrez)capturaPeca;
    }
    
    private PecasJogo fazMovimento(PosicaoJogo origem, PosicaoJogo destino){
        PecasXadrez p = (PecasXadrez)tabuleiro.removePeca(origem);
        //p.encrementaContagemMovimento();
        PecasJogo capturaPeca = tabuleiro.removePeca(destino);
        tabuleiro.colocarPeca(p, destino);
        
        if(capturaPeca != null){
            pecasNoTabuleiro.remove(capturaPeca);
            pecasCapturada.add(capturaPeca);
        }
        
        return capturaPeca;
    }
    
    private void desfazerMovimento(PosicaoJogo origem, PosicaoJogo destino, PecasJogo capturaPeca){
        PecasXadrez p = (PecasXadrez)tabuleiro.removePeca(destino);
        //p.decrementaContagemMovimento();
        tabuleiro.colocarPeca(p, origem);
        
        if(capturaPeca != null){
            tabuleiro.colocarPeca(capturaPeca, destino);
            pecasCapturada.remove(capturaPeca);
            pecasNoTabuleiro.add(capturaPeca);
        }
    }            
            
    private void validarPosicaoOrigem(PosicaoJogo posicao){
        if(!tabuleiro.existePeca(posicao)){
            throw new XadrezExcecao("Não existe posição de origem!");
        }
        if(jogadorAtual != ((PecasXadrez)tabuleiro.peca(posicao)).getCor()){
            throw new XadrezExcecao("A peça escolhida não é sua!");
        }
        if(!tabuleiro.peca(posicao).existeUmPossivelMovimento()){
            throw new XadrezExcecao("Não existe movimentos possíveis para a peça escolhida!");
        }
    }
    
    private void validarPosicaoDestino(PosicaoJogo origem, PosicaoJogo destino){
        if(!tabuleiro.peca(origem).possiveisMovimentos(destino)){
            throw new XadrezExcecao("A peça escolhida não pode se mover para a posição de destino!");
        }
    }
    
    private void proximoVirar(){
        virar++;
        jogadorAtual = (jogadorAtual == Cores.BRANCO)? Cores.PRETO : Cores.BRANCO;
    }
    
    private Cores oponente(Cores cor){
        return (cor == Cores.BRANCO)? Cores.PRETO : Cores.BRANCO;
    }
    
    private PecasXadrez rei(Cores cor){
        List<PecasJogo> lista = pecasNoTabuleiro.stream().filter(x -> ((PecasXadrez)x).getCor() == cor).collect(Collectors.toList());
        for(PecasJogo p: lista){
            if(p instanceof Rei){
                return (PecasXadrez)p;
            }
        }
        throw new IllegalStateException("Não existe o rei " + cor + " no tabuleiro!");
    }
    
    private boolean testarCheck(Cores cor){
        PosicaoJogo posicaoRei = rei(cor).peguePosicaoXadrez().posicionar();
        List<PecasJogo> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((PecasXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for(PecasJogo p: pecasOponente){
            boolean[][] matriz = p.possiveisMovimentos();
            if(matriz[posicaoRei.getLinha()][posicaoRei.getColuna()]){
                return true;
            }
        }
        return false;
    }
    
    private boolean testarCheckMate(Cores cor){
        if (!testarCheck(cor)){
            return false;
        }
        List<PecasJogo> lista = pecasNoTabuleiro.stream().filter(x -> ((PecasXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (PecasJogo p: lista){
            boolean[][] matriz = p.possiveisMovimentos();
            for(int i = 0; i < tabuleiro.getLinhas(); i++){
                for(int j = 0; j < tabuleiro.getColunas(); j++){
                    if(matriz[i][j]){
                        PosicaoJogo origem = ((PecasXadrez)p).peguePosicaoXadrez().posicionar();
                        PosicaoJogo destino = new PosicaoJogo(i, j);
                        PecasJogo pecaCapturada = fazMovimento(origem, destino);
                        boolean testaCheck = testarCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if(!testaCheck){
                            return false;
                        }
                        
                    }
                }
            }
        }
        return true;
    }
    
    private void colocarNovaPeca(char coluna, int linha, PecasXadrez peca){
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
        pecasNoTabuleiro.add(peca);
    }
    
    private void configuracaoInicial(){
       
        colocarNovaPeca('a', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('b', 1, new Cavaleiro(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('c', 1, new Bispo(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('d', 1, new Rainha(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('f', 1, new Bispo(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('g', 1, new Cavaleiro(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('h', 1, new Torre(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('a', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('b', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('c', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('d', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('e', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('f', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('g', 2, new Peao(tabuleiro, Cores.BRANCO));
        colocarNovaPeca('h', 2, new Peao(tabuleiro, Cores.BRANCO));
        
        colocarNovaPeca('a', 8, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('b', 8, new Cavaleiro(tabuleiro, Cores.PRETO));
        colocarNovaPeca('c', 8, new Bispo(tabuleiro, Cores.PRETO));
        colocarNovaPeca('d', 8, new Rainha(tabuleiro, Cores.PRETO));
        colocarNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO));
        colocarNovaPeca('f', 8, new Bispo(tabuleiro, Cores.PRETO));
        colocarNovaPeca('g', 8, new Cavaleiro(tabuleiro, Cores.PRETO));
        colocarNovaPeca('h', 8, new Torre(tabuleiro, Cores.PRETO));
        colocarNovaPeca('a', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('b', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('c', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('d', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('e', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('f', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('g', 7, new Peao(tabuleiro, Cores.PRETO));
        colocarNovaPeca('h', 7, new Peao(tabuleiro, Cores.PRETO));        
    }
}
