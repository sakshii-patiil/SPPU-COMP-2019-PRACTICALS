/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include <iostream>
#include<string>

using namespace std;

class job
{
    string name[50];
    int profit[50];
    int deadline[50];
    int jobCount;
    string scheduling[50];
    
    public:
    void getJobDetails();
    void jobScheduling();
};

void job :: getJobDetails()
{
    cout<<"Enter job Count : ";
    cin>>jobCount;
    
    cout<<"Enter name profit deadline\n ";
    for(int i=0;i<jobCount;i++)
    {
        cin>>name[i]>>profit[i]>>deadline[i];
    }
}

void job::jobScheduling()
{
    int tempProfit,tempDeadline;
    string tempName;
    int maxDeadline=deadline[0];
    
    for(int i=0;i<jobCount;i++)
    {
        if(deadline[i]>maxDeadline)
            maxDeadline=deadline[i];
    }
    
    
    
    for(int i=0;i<jobCount;i++)
    {
        for(int j=i+1;j<jobCount-i-1;j++)
        {
            if(profit[j]<profit[j+1])
            {
                tempProfit = profit[j];
                tempName = name[j];
                tempDeadline = deadline[j];
                profit[j] = profit[j+1];
                name[j] = name[j+1];
                deadline[j] = deadline[j+1];
                profit[j+1] = tempProfit;
                name[j+1] = tempName;
                deadline[j+1] = tempDeadline;
            }
        }
    }
    int j = deadline[0]-1;
    for (int i = 0; i < jobCount; i++) {
        if(deadline[i]==maxDeadline)
        {
            scheduling[maxDeadline-1] = name[i]; 
        }
       else if(deadline[i]<maxDeadline && scheduling[j]=="" && j>-1)
        {
            scheduling[j] = name[i];   
        }else if(deadline[i]<maxDeadline && scheduling[j]!="")
        {
            j--;
            i--;
        }
    }
    
    for(int i=0;i<maxDeadline;i++)
    {
        cout<<scheduling[i]<<" ";
    }
    
    
}

int main()
{
   
   job obj;
   obj.getJobDetails();
   obj.jobScheduling();

    return 0;
}