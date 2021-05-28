import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite {

    public static int TAMANHO_MAXIMO_TUITES = 120;

    private Map<String, Usuario> usuarioByEmail;

    private Map<String, Integer> contadorByHashtag;
    private String hashtagMaisComum;
    private int contadorDaHashtagMaisComum;

    public TuiterLite() {
        this.usuarioByEmail = new HashMap<>();
        this.contadorByHashtag = new HashMap<>();
        this.hashtagMaisComum = null;
        this.contadorDaHashtagMaisComum = 0;
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {  // throws UsuarioJaExisteException {
        Usuario usuarioPreExistente = this.usuarioByEmail.get(email);
        if (usuarioPreExistente != null) {
            return usuarioPreExistente;
        }

        Usuario novoUsuario = new Usuario(nome, email);
        this.usuarioByEmail.put(email, novoUsuario);

        return novoUsuario;
    }

    /**
     *
     * @param usuario O autor do tuíte (não deve ser nulo)
     * @param texto O texto desejado (nunca nulo)
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) throws UsuarioDesconhecidoException {

        if (texto == null || texto.length() == 0 || texto.length() > TAMANHO_MAXIMO_TUITES ||
                usuario == null || !this.usuarioByEmail.containsKey(usuario.getEmail())) {
            return null;
        }

        Set<String> hashtags = obterHashtags(texto);
        Tuite tuite = new Tuite(usuario, texto, hashtags);

        // atualiza os contadores de hashtags do sistema
        for (String hashtag : hashtags) {
            int contadorAtual = this.contadorByHashtag.getOrDefault(hashtag, 0);
            int novoContador = contadorAtual + 1;
            this.contadorByHashtag.put(hashtag, novoContador);

            if (novoContador > this.contadorDaHashtagMaisComum) {
                this.hashtagMaisComum = hashtag;
                this.contadorDaHashtagMaisComum = novoContador;
            }
        }

        return tuite;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        return this.hashtagMaisComum;
    }

    private Set<String> obterHashtags(String texto) {

        Set<String> hashtags = null;

        String[] palavrasDaFrase = texto.split("([^(a-z|A-Z|0-9|ã-ü|#)])+");   // "escapando" a contrabarra para que minha string seja "CONTRABARRA S"

        for (String palavra : palavrasDaFrase) {

            if (palavra.charAt(0) == '#') {
                palavra = palavra.replaceAll("#+", "#");

                String[] tags = palavra.split("#");
                for (String tag : tags) {
                    if (tag.length() > 0) {

                        if (hashtags == null) {  // lazy instantiation
                            hashtags = new HashSet<>();
                        }
                        hashtags.add("#" + tag);
                    }
                }
            }
        }

        return hashtags == null ? Collections.emptySet() : hashtags;
    }

    private static boolean isAlphanumeric(char c) {
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }



}
