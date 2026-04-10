$env:Path = [System.Environment]::GetEnvironmentVariable("Path","Machine") + ";" + [System.Environment]::GetEnvironmentVariable("Path","User")

cd "C:\Users\ACER\OneDrive\Desktop\week 3 4"

# Set base file
$FullCode = Get-Content -Raw "BankingAlgorithms.java"

@'
import java.util.*;

public class BankingAlgorithms {

    public static void main(String[] args) {
        System.out.println("Algorithms App starting...");
    }
}
'@ | Set-Content "BankingAlgorithms.java"

git commit --amend -m "Base application setup" -a
git push -f origin main

git checkout -b develop
git push -u origin develop

# I will just write a script to do commits for each problem, pulling the relevant snippet from $FullCode.
# For simplicity, let's just commit the entire file bit by bit.
$Lines = $FullCode -split "`n"
$UC1 = $Lines[0..82] -join "`n"
$UC2 = $Lines[0..136] -join "`n"
$UC3 = $Lines[0..204] -join "`n"
$UC4 = $Lines[0..255] -join "`n"
$UC5 = $Lines[0..296] -join "`n"
$UC6 = $Lines[0..331] -join "`n"
$FullMain = $FullCode

# UC1
git checkout develop
git checkout -b feature/UC1
$UC1 | Set-Content "BankingAlgorithms.java"
git add .
git commit -m "UC1: Display Consist Summary / Transaction Fee Sorting"
git push origin feature/UC1
git checkout develop
git merge feature/UC1
git push origin develop

# UC2
git checkout develop
git checkout -b feature/UC2
$UC2 | Set-Content "BankingAlgorithms.java"
git add .
git commit -m "UC2: Add Passenger Bogies / Client Risk Score"
git push origin feature/UC2
git checkout develop
git merge feature/UC2
git push origin develop

# UC3
git checkout develop
git checkout -b feature/UC3
$UC3 | Set-Content "BankingAlgorithms.java"
git add .
git commit -m "UC3: Historical Trade Volume Analysis"
git push origin feature/UC3
git checkout develop
git merge feature/UC3
git push origin develop

# UC4
git checkout develop
git checkout -b feature/UC4
$UC4 | Set-Content "BankingAlgorithms.java"
git add .
git commit -m "UC4: Portfolio Return Sorting"
git push origin feature/UC4
git checkout develop
git merge feature/UC4
git push origin develop

# UC5
git checkout develop
git checkout -b feature/UC5
$UC5 | Set-Content "BankingAlgorithms.java"
git add .
git commit -m "UC5: Account ID Lookup"
git push origin feature/UC5
git checkout develop
git merge feature/UC5
git push origin develop

# UC6
git checkout develop
git checkout -b feature/UC6
$FullMain | Set-Content "BankingAlgorithms.java"
git add .
git commit -m "UC6: Risk Threshold Binary Lookup and Main Wrapper"
git push origin feature/UC6
git checkout develop
git merge feature/UC6
git push origin develop

