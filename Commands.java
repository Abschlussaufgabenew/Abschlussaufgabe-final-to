package edu.kit.informatik;


import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public enum Commands {
	
	ADD_ADMIN("add-admin (\\w+);(\\w+);(\\w+);(\\w+)"){
		@Override
		public void execute (MatchResult matcher , OlympicGames olympicGames) throws InputException{
			String firstName = matcher.group(1);
			String lastName = matcher.group(2);
			String userName = matcher.group(3);
			String passWord = matcher.group(4);
			
		      olympicGames.addAdmin(firstName, lastName, userName, passWord);
		      Terminal.printLine("OK");
		}
		
	},
	
	LOGIN_ADMIN("login-admin (\\w+);(\\w+)"){
		
		@Override
		public void execute (MatchResult matcher , OlympicGames olympicGames) throws InputException{
			String userName = matcher.group(1);
			String passWord = matcher.group(2);
			
			olympicGames.loginAdmin(userName , passWord);
			Terminal.printLine("OK");
		}
	},
	
	LOGOUT_ADMIN("logout-admin"){
		@Override
		public void execute (MatchResult matcher , OlympicGames olympicGames) throws InputException{
			olympicGames.logoutAdmin();
			Terminal.printLine("OK");
		}
	},
	
	ADD_SPORTS_VENUE("add-sports-venue (\\d+);(\\w+);(\\w+);(\\w+);(\\d+);(\\d+)"){
		@Override
		public void execute (MatchResult matcher , OlympicGames olympicGames) throws InputException{
			int id = Integer.parseInt(matcher.group(1));
			String countryName = matcher.group(2);
			String location = matcher.group(3);
			String name = matcher.group(4);
			int openingYear = Integer.parseInt(matcher.group(5));
			int seatsNumber = Integer.parseInt(matcher.group(6));
			
			olympicGames.addSportsVenue(id, countryName, location, name, openingYear, seatsNumber);
			Terminal.printLine("OK");
		}
	},
	
	LIST_SPORTS_VENUES("list-sports-venues (\\w+)"){
		@Override
		public void execute (MatchResult matcher , OlympicGames olympicGames) throws InputException{
			String countryName = matcher.group(1);
			
			olympicGames.listSportsVenues(countryName);
			
		}
	},
	
	ADD_OLYMPIC_SPORT("add-olympic-sport (\\w+);(\\w+)"){
		@Override
		public void execute(MatchResult matcher , OlympicGames olympicGames) throws InputException{
		
			String sportsType = matcher.group(1);
			String sportsDiscipline = matcher.group(2);
			
			olympicGames.addOlympicSport(sportsType, sportsDiscipline);
			Terminal.printLine("OK");
		}
	},
	
	LIST_OLYMPIC_SPORTS("list-olympic-sports"){
		@Override 
		public void execute(MatchResult matcher , OlympicGames olympicGames) throws InputException{
		
			olympicGames.listOlympicSports();
			
		}
	},
	
	ADD_IOC_CODE("add-ioc-code (\\d+);(\\w+);(\\w+);(\\d+)"){
		@Override
		public void execute(MatchResult matcher , OlympicGames olympicGames) throws InputException{
			int id = Integer.parseInt(matcher.group(1));
			String ioc = matcher.group(2);
			String country = matcher.group(3);
			int year = Integer.parseInt(matcher.group(4));
			
			olympicGames.addIocCode(country, id, ioc, year);
			Terminal.printLine("OK");
		}
	},
	
	LIST_IOC_CODES("list-ioc-codes"){
		@Override
		public void execute(MatchResult matcher , OlympicGames olympicGames) throws InputException{
			olympicGames.listIocCodes();
		}
	},
	
	ADD_ATHLETE("add-athlete (\\d+);(\\w+);(\\w+);(\\w+);(\\w+);(\\w+)") {
		@Override
		public void execute(MatchResult matcher , OlympicGames olympicGames) throws InputException{
			String id = matcher.group(1);
			String firstName = matcher.group(2);
			String lastName = matcher.group(3);
			String country = matcher.group(4);
			String sport = matcher.group(5);
			String discipline = matcher.group(6);
			olympicGames.addAthlete(id, firstName, lastName, country, sport, discipline);
			Terminal.printLine("OK");
		}
	},
	
	SUMMARY_ATHLETES("summary-athletes (\\w+);(\\w+)"){
		@Override
		public void execute(MatchResult matcher , OlympicGames olympicGames) throws InputException{
		
			String sport = matcher.group(1);
			String discipline = matcher.group(2);
			olympicGames.summaryAthletes(sport, discipline);
		}
	},
			
	
	 QUIT("quit") {
        @Override
        public void execute(MatchResult matcher, OlympicGames olympicGames) throws InputException {
            isRunning = false;
        }
    };
	
	

	 private static boolean isRunning = true;
	    private Pattern pattern;
	
	 Commands(String pattern) {
	        this.pattern = Pattern.compile(pattern);
	    }
	 
	 public abstract void execute(MatchResult matcher, OlympicGames olympicGame) throws InputException;
	 
	 public boolean isRunning() {
	        return isRunning;
	    }
	 
	 public static Commands executeMatching(String input, OlympicGames olympicGame) throws InputException {
	        for (Commands command : Commands.values()) {
	            Matcher matcher = command.pattern.matcher(input);
	            if (matcher.matches()) {
	                command.execute(matcher, olympicGame);
	                return command;
	            }
	        }

	        throw new InputException("not a valid command!");
	    }
	}
