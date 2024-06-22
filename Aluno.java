class Aluno implements Comparable<Aluno> {
    private String nome;
    private long ra;

    public Aluno(String nome, long ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public long getRA() {
        return ra;
    }

    @Override
    public int compareTo(Aluno outroAluno) {
        return this.nome.compareToIgnoreCase(outroAluno.getNome());
    }

    @Override
    public String toString() {
        return "Aluno [Nome = " + nome + ", RA = " + ra + "]";
    }
}
