package jihogrammer.design_patterns.singleton.break_way.reflection_api.avoid;

public enum Settings {
    
    INSTANCE;

    // defualt 접근 제한자가 private이다.
    // 이하 코드들을 작성하지 않아도 문제 없다.
    Settings() {}

    // enum class examples
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
