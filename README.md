# unity-recrutiment
Zadanie rekrutacyjne do firmy UnityGroup

### Diagram klas
![image](./class_diagram.png)

### Dodatkowe założenia

Oto założenia, które przyjąłem, a nie wynikają bezpośrednio z treści:
- ceny i rabaty są reprezentowane jako liczby wymierne z maksymalnie dwoma miejscami po przecinku (reprezentacja złotych i groszy)
- "idealne proporcje" to takie, wg których rabat dla danego produktu jest liczbą zgodną z ustaloną wyżej reprezentacją rabatu (tj. z dokładnością do grosza)
- gdy proporcje nie są idealne, wyliczony rabat jest zaokrąglany w dół, a nadwyżki są dodawane do ostatniego produktu. 
  Powoduje to jednak problem, ponieważ ostatni produkt może mieć bardzo niską cenę i nie będzie można dodać całej nadwyżki
  ze wszystkich pozostałych produktów (rabat na pojedynczy produkt nie może przekraczać jego ceny). Wtedy naliczony na ostatni produkt rabat
  równy jest jego cenie, a suma rabatów nie jest równa całemu rabatowi. Próba rozłożenia nadwyżki z zaokrąglenia na inne produkty
  mogłaby być rozwiązaniem, ale byłaby sprzeczna z wymaganiami zadania.