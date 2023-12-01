package LabFP.Lab22;

public interface AccionMilitar {
  void atacar(Soldado enemigo);
  void defender();
  void avanzar();
  void retroceder();
  void serAtacado(int dano);
  void huir();
  void morir();
  
}
