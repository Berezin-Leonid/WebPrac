package web.models;

public interface CommonEntity<ID> {
    ID getId();
    void setId(ID id);
}