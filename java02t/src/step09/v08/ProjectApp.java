/*
 * 작업 목표: 프로젝트 관리 시스템 구현하기
 */
package step09.v08;

import java.sql.Date;
import java.util.Scanner;

public class ProjectApp {
  Scanner scanner = new Scanner(System.in);
  Project[] projects = new Project[1000];
  int pos; // 저장 위치

  public static void main(String[] args) {
    ProjectApp app = new ProjectApp();
    app.service();
  }
  
  private void service() {
    String command = null;
    do {
      System.out.print("명령> ");
      command = scanner.nextLine(); 
      switch (command) {
      case "list":
        doList();
        break;
      case "add":
        doAdd();
        break;
      case "delete":
        doDelete();
        break;  
      case "help":
        doHelp();
        break;
      case "quit":
        doQuit();
        break;
      default:
        System.out.println("해당 명령을 지원하지 않습니다.");
      }
    } while (!command.equals("quit"));
    
    System.out.println("안녕히가세요!");
    scanner.close();
  }

  private void doList() {
    System.out.printf("%-3s %-20s %-10s %-10s %-40s\n", 
        "No", "Title", "Start", "End", "Members");

    for (int i = 0; i < pos; i++) {
      if (projects[i] == null) // 배열의 항목이 null인 경우, 다음 항목으로 바로 이동.
        continue;
      System.out.printf("% 3d %-20s %3$tY-%3$tm-%3$td %4$s %5$-40s\n", 
          i, 
          projects[i].title,
          projects[i].startDate,
          projects[i].endDate,
          projects[i].member);
    }
  }
  
  private void doAdd() {
    Project project = new Project();
    
    System.out.print("프로젝트명? ");
    project.setTitle(scanner.nextLine());
    
    System.out.print("시작일? ");
    project.setStartDate(Date.valueOf(scanner.nextLine()));
    
    System.out.print("종료일? ");
    project.setEndDate(Date.valueOf(scanner.nextLine()));

    System.out.print("멤버? ");
    project.setMember(scanner.nextLine());
    
    System.out.print("정말 저장하시겠습니까?(y/n)");
    String yesno = scanner.nextLine();
    if (yesno.toLowerCase().equals("y")) {
      if (pos < projects.length) { 
        projects[pos++] = project;
        System.out.println("저장되었습니다.");
      } else {
        System.out.println("저장소가 모두 찼습니다.\n저장할 수 없습니다!");
      }
    } else {
      System.out.println("취소하였습니다.");
    }
  }
  
  private void doDelete() {
    System.out.print("프로젝트 번호? ");
    int no = Integer.parseInt(scanner.nextLine());
    
    System.out.print("정말 삭제하시겠습니까?(y/n)");
    String yesno = scanner.nextLine();
    if (yesno.toLowerCase().equals("y")) {
      if (no > -1 && no < pos) { 
        if (projects[no] == null) {
          System.out.println("이미 삭제된 항목입니다.");
        } else {
          for (int i = no; i < (pos - 1); i++) {
            projects[i] = projects[i + 1];
          }
          pos--;
          System.out.println("삭제하였습니다.");
        }
      } else {
        System.out.println("유효하지 않은 번호입니다.");
      }
    } else {
      System.out.println("취소하였습니다.");
    }
  }
  
  private void doQuit() {
    
  }
  
  private void doHelp() {
    System.out.println("[사용법]");
    System.out.println("명령");
    System.out.println();
    System.out.println("[명령]");
    System.out.println("list         프로젝트 목록을 리턴한다.");
    System.out.println("add          프로젝트를 추가한다.");
    System.out.println("delete       프로젝트를 삭제한다. ");
    System.out.println("quit         프로그램을 종료한다.");
  }

}
