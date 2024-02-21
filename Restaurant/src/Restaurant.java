public class Restaurant {
    // 결합도 높은 코드

    // 객체 형성
//    Chef chef = new Chef();
//    NewChef chef = new NewChef();
    Cookable chef = new Chef();
    Cookable newChef = new NewChef();

    // 직원이 바뀔때마다 명령어가 바뀐다 = 결합도가 높다

    public void job(){
//        chef.cook();
//        chef.goodCook();
        chef.cook();
    }
}
